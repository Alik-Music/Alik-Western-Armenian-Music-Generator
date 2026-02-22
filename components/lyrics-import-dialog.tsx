"use client"

import { FileText, Search } from "lucide-react"
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { ScrollArea } from "@/components/ui/scroll-area"
import { useState } from "react"

interface LyricsItem {
  id: string
  title: string
  preview: string
  createdAt: string
}

const savedLyrics: LyricsItem[] = [
  {
    id: "1",
    title: "Midnight Drive",
    preview: "Cruising down the boulevard, city lights are shining bright...",
    createdAt: "2 days ago",
  },
  {
    id: "2",
    title: "Ocean Waves",
    preview: "The tide rolls in, the tide rolls out, washing all my fears away...",
    createdAt: "5 days ago",
  },
  {
    id: "3",
    title: "Rising Up",
    preview: "From the ashes, from the dust, we rise above the noise of rust...",
    createdAt: "1 week ago",
  },
  {
    id: "4",
    title: "Summer Haze",
    preview: "Lazy afternoons and lemonade, the sun is golden, the air is warm...",
    createdAt: "2 weeks ago",
  },
  {
    id: "5",
    title: "Neon Dreams",
    preview: "Electric pulses through my veins, neon signs reflect the rain...",
    createdAt: "3 weeks ago",
  },
]

interface LyricsImportDialogProps {
  open: boolean
  onOpenChange: (open: boolean) => void
  onSelect: (lyrics: string, title: string) => void
}

export function LyricsImportDialog({
  open,
  onOpenChange,
  onSelect,
}: LyricsImportDialogProps) {
  const [search, setSearch] = useState("")

  const filtered = savedLyrics.filter(
    (l) =>
      l.title.toLowerCase().includes(search.toLowerCase()) ||
      l.preview.toLowerCase().includes(search.toLowerCase())
  )

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="sm:max-w-md">
        <DialogHeader>
          <DialogTitle className="text-foreground">Import from Lyrics Library</DialogTitle>
          <DialogDescription className="text-muted-foreground">
            Select lyrics from your saved collection to use in your song.
          </DialogDescription>
        </DialogHeader>
        <div className="relative">
          <Search className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
          <Input
            placeholder="Search your lyrics..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="pl-9"
          />
        </div>
        <ScrollArea className="h-72">
          <div className="flex flex-col gap-2 pr-4">
            {filtered.map((item) => (
              <button
                key={item.id}
                onClick={() => {
                  onSelect(item.preview, item.title)
                  onOpenChange(false)
                }}
                className="flex items-start gap-3 rounded-lg border border-border bg-card p-3 text-left transition-colors hover:border-secondary hover:bg-accent/20"
              >
                <div className="mt-0.5 flex h-8 w-8 shrink-0 items-center justify-center rounded-md bg-primary/10">
                  <FileText className="h-4 w-4 text-primary" />
                </div>
                <div className="min-w-0 flex-1">
                  <p className="text-sm font-semibold text-foreground">{item.title}</p>
                  <p className="mt-0.5 truncate text-xs text-muted-foreground">
                    {item.preview}
                  </p>
                  <p className="mt-1 text-xs text-muted-foreground/60">{item.createdAt}</p>
                </div>
              </button>
            ))}
            {filtered.length === 0 && (
              <div className="py-8 text-center text-sm text-muted-foreground">
                No lyrics found matching your search.
              </div>
            )}
          </div>
        </ScrollArea>
      </DialogContent>
    </Dialog>
  )
}
